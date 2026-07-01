# JobTracker 💼

O **JobTracker** é uma solução Full Stack moderna projetada para centralizar, organizar e monitorar candidaturas a vagas de emprego. A aplicação permite que profissionais em busca de recolocação ou novas oportunidades acompanhem o status de cada processo seletivo, armazenem histórico de entrevistas e gerenciem os próximos passos de forma eficiente.

---

## 🛠️ Funcionalidades Principais

* **Gerenciamento de Candidaturas:** Cadastro, edição e remoção de vagas de interesse (Empresa, Cargo, Link da Vaga, Salário Pretendido e Localização).
* **Funil de Recrutamento (Status):** Acompanhamento visual e atualização do estado de cada vaga (ex: *Currículo Enviado*, *Entrevista Marcada*, *Teste Técnico*, *Proposta Recebida*, *Recusado*).
* **Histórico de Interações:** Espaço para anotações personalizadas sobre feedbacks recebidos, pontos fortes identificados e perguntas frequentes de entrevistas.
* **Filtros e Busca Rápida:** Localização ágil de processos ativos por nome da empresa ou cargo.

---

## 🚀 Tecnologias Utilizadas

### Backend (API REST)
* **Java 21**
* **Spring Boot 4.1.0**
* **Spring Data JPA** (Camada de persistência de dados)
* **Hibernate** (Mapeamento Objeto-Relacional)
* **HikariCP** (Gerenciamento de Pool de conexões robusto)
* **PostgreSQL** (Banco de dados relacional de produção)

### Frontend
* **Tecnologias:** [Tailwind CSS / HTML, CSS, JavaScript]

### Infraestrutura e Deploy (Cloud)
* **Render:** * Hospedagem da API como *Web Service*.
  * Hospedagem do banco de dados relacional gerido (*PostgreSQL*).
  * Hospedagem do ecossistema visual como *Static Site*.
