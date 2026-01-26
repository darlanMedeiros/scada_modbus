import TagCard from "../TagCard/TagCard";
import "./Lampada.css";

export default function Lampada({ label, status }) {
  const ligado = status === 1 || status === "ON";

  return (
    <TagCard
      label={label}
      valor={
        <div className="lampada-container">
          <div className={`lampada ${ligado ? "lampada-on" : "lampada-off"}`} />
          <span className="lampada-texto">
            {ligado ? "Ligado" : "Desligado"}
          </span>
        </div>
      }
      unidade=""
    />
  );
}