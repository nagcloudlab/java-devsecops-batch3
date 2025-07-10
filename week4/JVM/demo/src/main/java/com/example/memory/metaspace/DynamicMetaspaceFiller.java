package com.example.memory.metaspace;

import javax.tools.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.*;

public class DynamicMetaspaceFiller {
    public static void main(String[] args) throws Exception {
        List<ClassLoader> loaders = new ArrayList<>();
        int count = 0;

        while (true) {
            String className = "Dummy" + count;
            String sourceCode = generateSource(className);

            // Compile source in-memory
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            JavaFileObject javaFile = new InMemoryJavaFileObject(className, sourceCode);
            JavaFileManager fileManager = new ForwardingJavaFileManager<>(
                    compiler.getStandardFileManager(null, null, null)) {
                @Override
                public JavaFileObject getJavaFileForOutput(Location location, String className,
                        JavaFileObject.Kind kind, FileObject sibling) {
                    return javaFile;
                }
            };

            Boolean result = compiler.getTask(null, fileManager, null, null, null, Collections.singletonList(javaFile))
                    .call();
            if (!result) {
                throw new RuntimeException("Compilation failed for " + className);
            }

            // Load compiled class using new class loader
            InMemoryClassLoader loader = new InMemoryClassLoader(className,
                    ((InMemoryJavaFileObject) javaFile).getBytes());
            Class<?> clazz = loader.loadClass(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            System.out.println("Loaded class: " + className + " using " + loader);
            loaders.add(loader); // prevent GC
            count++;
        }
    }

    static String generateSource(String className) {
        return "public class " + className + " {\n" +
                "  static {\n" +
                "    System.out.println(\"" + className + " loaded\");\n" +
                "  }\n" +
                "}";
    }

    // In-memory source file
    static class InMemoryJavaFileObject extends SimpleJavaFileObject {
        private final String source;
        private ByteArrayOutputStream byteCode = new ByteArrayOutputStream();

        InMemoryJavaFileObject(String name, String source) {
            super(URI.create("string:///" + name + Kind.SOURCE.extension), Kind.SOURCE);
            this.source = source;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return source;
        }

        @Override
        public OutputStream openOutputStream() {
            return byteCode;
        }

        byte[] getBytes() {
            return byteCode.toByteArray();
        }
    }

    // In-memory class loader
    static class InMemoryClassLoader extends ClassLoader {
        private final String name;
        private final byte[] bytecode;

        public InMemoryClassLoader(String name, byte[] bytecode) {
            this.name = name;
            this.bytecode = bytecode;
        }

        @Override
        protected Class<?> findClass(String className) throws ClassNotFoundException {
            if (!this.name.equals(className))
                return super.findClass(className);
            return defineClass(className, bytecode, 0, bytecode.length);
        }
    }
}