


### deploy jenkins server

```bash
wget https://get.jenkins.io/war-stable/2.504.3/jenkins.war
java -jar jenkins.war
```

### deploy sonarqube-server

```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:community
```

### deploy nexus artifactory
```
docker run -d \
  --name nexus \
  -p 8081:8081 \
  -v nexus-data:/nexus-data \
  sonatype/nexus3
```

---------------------------------------------

-> create pipeline project in jenkins
-> confifure sonar & nexus credentials in jenkins server

----------------------------------------------

-> trigger build manually
