# 🔌 OnOff GUI com Arduino UNO + Relé  
Interface gráfica em Java (Swing + Maven) integrada ao Arduino UNO via porta serial, permitindo ligar e desligar dispositivos externos como uma fonte de 12V através de um módulo relé.

---

## 📖 Sobre o Projeto
Este projeto implementa:

- Uma **interface gráfica em Java** com botão Ligar/Desligar  
- Comunicação com o **Arduino UNO** via porta serial  
- Controle de um **relé** que ativa ou corta a energia de uma fonte externa (ex.: 12V)  
- Envio de comandos `'1'` para ligar e `'0'` para desligar  
- Retorno do Arduino exibido no console (ex.: `RELAY ON`, `RELAY OFF`)  

Tudo isso usando:

- Java 17  
- Maven  
- Biblioteca jSerialComm  
- Arduino UNO (Sketch incluso)  

---

# 🖥️ Interface Java (Swing)
A interface possui:

- Combobox para seleção da porta serial  
- Botão **Conectar**  
- Botão **Ligar / Desligar**  
- Indicação visual do estado  
- Log no console da IDE


---

# ⚙️ Como Rodar o Projeto Java

### 1️⃣ Instale:
- **JDK 17**
- **Maven**
- **Drivers do Arduino (CH340/ATmega)**

### 2️⃣ Compile:
```bash
mvn clean compile

Como Usar
🔌 Conecte o Arduino

Plugue no USB

Verifique a porta (COM3, COM4, etc.)

📨 Na interface Java:

Escolha a porta serial na lista

Clique Conectar

Clique Ligar / Desligar

O Java envia '1' ou '0'

O Arduino ativa/desativa o relé



---

## 📁 Estrutura do Projeto
