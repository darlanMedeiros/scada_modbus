import "./TagCard.css";

export default function TagCard({ label, valor, unidade }) {
  return (
    <div className="card">
      <span className="label">{label}</span>
      <span className="value">
        {valor} {unidade}
      </span>
    </div>
  );
}
