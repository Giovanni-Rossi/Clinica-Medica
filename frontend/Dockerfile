# Etapa 1: build da aplicação React
FROM node:20-alpine AS builder
WORKDIR /app

# Recebe o arg em compile-time e torna-o ENV para o CRA
ARG REACT_APP_API_URL=/api
ENV REACT_APP_API_URL=$REACT_APP_API_URL

COPY package.json yarn.lock ./
RUN yarn install --frozen-lockfile

COPY . .
RUN yarn build

# Etapa 2: servidor com Nginx
FROM nginx:stable-alpine AS production
COPY --from=builder /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Opcional, só para documentação interna
#EXPOSE 80

# Comando padrão
CMD ["nginx", "-g", "daemon off;"]
