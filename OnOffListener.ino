// OnOffRelay.ino
const int RELAY_PIN = 8; // pino digital para controlar o relé

void setup() {
  pinMode(RELAY_PIN, OUTPUT);
  digitalWrite(RELAY_PIN, LOW); // assumindo que LOW = desativado; ajuste se necessário
  Serial.begin(9600);
  Serial.println("Arduino pronto");
}

void loop() {
  if (Serial.available() > 0) {
    char c = Serial.read();
    if (c == '1') {
      digitalWrite(RELAY_PIN, HIGH); // ativa relé (passa 12V)
      Serial.println("RELAY ON");
    } else if (c == '0') {
      digitalWrite(RELAY_PIN, LOW);  // desliga relé (corta 12V)
      Serial.println("RELAY OFF");
    }
    // ignorar outros caracteres
  }
}

