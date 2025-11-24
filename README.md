# OnOff GUI com Arduino (Java + Swing + jSerialComm)

Projeto mínimo Java (Maven) que abre uma janela com um botão **Ligar / Desligar**.
Inclui suporte básico para enviar comandos via porta serial para um Arduino.

## Estrutura
```
onoff-gui/
├─ pom.xml
├─ README.md
├─ .gitignore
├─ src/
│  └─ main/
│     └─ java/
│        └─ com/
│           └─ example/
│              └─ OnOffApp.java
└─ arduino/
   └─ OnOffListener.ino
```

## Requisitos
- Java JDK (11, 17 recomendado)
- Maven
- Arduino IDE (ou `arduino-cli`) para subir o sketch
- Cabo USB para conectar o Arduino ao computador

## Como rodar localmente (passo-a-passo)

1. Clone ou descompacte o projeto:
```bash
# se usar o ZIP que eu gerei, descompacte:
unzip onoff-gui-with-arduino.zip -d onoff-gui
cd onoff-gui
```

2. Compilar com Maven:
```bash
mvn clean compile
```

3. Rodar o programa Java (vai abrir a janela):
```bash
mvn exec:java
```

4. Na janela, selecione a porta serial (ex: `COM3` no Windows ou `/dev/ttyACM0` no Linux) e clique em **Conectar**.
Depois use o botão **LIGAR/DESLIGAR**; o app tentará enviar `1` (ligar) ou `0` (desligar) pela serial.

## Arduino - carregar o sketch
1. Abra o arquivo `arduino/OnOffListener.ino` na Arduino IDE.
2. Selecione a placa (ex.: Arduino Uno) e a porta correta.
3. Clique em **Upload** para enviar o sketch ao Arduino.
4. Conecte o Arduino por USB ao computador. Quando o Java estiver conectado na mesma porta, o LED do pino 13 será controlado pelos comandos.

## Notas sobre jSerialComm
- A dependência `com.fazecast:jSerialComm` já está adicionada no `pom.xml`.
- O programa lista portas disponíveis usando `SerialPort.getCommPorts()`.
- Se nenhuma porta for listada, verifique drivers (especialmente em Windows: drivers CH340/FTDI) e se o Arduino está conectado via USB.

## Dicas de solução de problemas
- Se a porta não aparecer no combo:
  - Verifique se o cabo USB é apenas de energia (alguns cabos não têm dados).
  - Verifique drivers no sistema (Windows: Device Manager; Linux: `dmesg`).
- Se receber erro ao abrir a porta: feche programas que usam a mesma porta (por exemplo, Arduino IDE Serial Monitor).
- Logs aparecem no console do Java (quando executado via Maven) e o GUI mostrará mensagens.

## Próximos passos (opcional)
- Enviar strings mais complexas (ex.: `ON`/`OFF`) e ler resposta do Arduino.
- Adicionar reconexão automática e tratamento de erros mais robusto.
- Integrar com um protocolo simples (JSON) para múltiplos comandos.

---
Boa sorte! Se quiser, eu posso também:
- Gerar um artefato jar executável (fat-jar) pronto.
- Mostrar como usar `arduino-cli` para fazer upload sem a IDE.
- Adaptar o exemplo para JavaFX em vez de Swing.
