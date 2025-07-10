

# Prometheus Deployment

docker run -d -p 9090:9090 \
  -v "$(pwd)/prometheus.yml:/etc/prometheus/prometheus.yml" \
  -v "$(pwd)/alert.rules.yml:/etc/prometheus/alert.rules.yml" \
  prom/prometheus


# Grafana Deployment
docker run -d -p 3000:3000 grafana/grafana

# Zipkin Deployment
docker run -d -p 9411:9411 openzipkin/zipkin

# OpenTelemetry Collector Deployment
docker run --rm -p 4318:4318 -p 9464:9464 \
  -v $(pwd)/otel-collector-config.yaml:/etc/otel/config.yaml \
  otel/opentelemetry-collector:latest \
  --config=/etc/otel/config.yaml

