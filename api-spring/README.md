API-SPRING
CRIAR BUILD EM JAR
terminal> mvn clean package
rodar > mvn Spring-boot:run
ou> target\*.jar

Não rodar dentro OneDrive

✅ O QUE FUNCIONA (e é padrão de mercado)
🥇 OPÇÃO 1 — Build do Vite → servir pelo Spring Boot (RECOMENDADO)
Essa é a melhor solução para teu caso.
🔹 Como funciona
Vite gera arquivos estáticos (dist/)
Spring Boot serve esses arquivos
Resultado: UM ÚNICO JAR
Node não é necessário em produção

🔧 Passo a passo
1️⃣ Build do frontend
cd web
npm run build
Vai gerar:
web/dist/
├─ index.html
├─ assets/

2️⃣ Copiar dist para o Spring Boot
Copie tudo para:
api-spring/src/main/resources/static/
Estrutura final:
src/main/resources/static/
├─ index.html
├─ assets/

3️⃣ Controller fallback (SPA)
@Controller
public class FrontendController {

@GetMapping({"/", "/{path:^(?!api).\*}/**"})
public String index() {
return "forward:/index.html";
}
}
👉 Isso garante:
/ → frontend
/api/** → backend
refresh funciona no React/Vite

4️⃣ Build final
mvn clean package -DskipTests
👉 Resultado:
target/api-spring-1.0.0.jar

5️⃣ Rodar
java -jar api-spring-1.0.0.jar
✔ Backend
✔ Frontend
✔ Um único processo
✔ Um único JAR
