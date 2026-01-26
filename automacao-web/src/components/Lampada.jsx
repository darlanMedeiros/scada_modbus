import "./Lampada.css";

export default function Lampada({ ligada, label, piscar = false }) {
  let statusClass = "lampada-erro";
  let texto = "SEM DADO";

  if (ligada === true) {
    statusClass = piscar ? "lampada-on lampada-pisca" : "lampada-on";
    texto = "ATIVA";
  } else if (ligada === false) {
    statusClass = "lampada-off";
    texto = "DESLIGADA";
  }

  return (
    <div className="card lampada-card">
      <h3>{label}</h3>

      <div className={`lampada ${statusClass}`} />

      <span className="lampada-texto">{texto}</span>
    </div>
  );
}
