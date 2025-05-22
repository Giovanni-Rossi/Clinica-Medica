
FROM node:20-alpine as builder
WORKDIR /app
COPY package.json ./
RUN yarn install --frozen-lockfile
COPY . .
RUN yarn build

# Production stage: serve com Nginx
FROM nginx:stable-alpine AS production
COPY --from=builder /app/build /usr/share/nginx/html
EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]