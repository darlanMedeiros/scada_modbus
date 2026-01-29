import TagCard from "../TagCard/TagCard";
import "./Lampada.css";

export default function Lampada({ label, ligada, piscar }) {
  return (
    <TagCard
      label={label}
      valor={
        <div className="lampada-container">
          <div
            className={`lampada ${ligada ? "lampada-on" : "lampada-off"
              } ${piscar ? "lampada-piscar" : ""}`}
          />
          <span className="lampada-texto">
            {ligada ? "Ligado" : "Desligado"}
          </span>
        </div>
      }
      unidade=""
    />
  );
}