import React, { Component } from "react";
import TagCard from "./components/TagCard";
import Lampada from "./components/Lampada";
import { buscarTag } from "./services/api";


const TAGS = [
  { key: "TEMPERATURA", label: "🌡 Temperatura", unidade: "°C" },
  { key: "PRESSAO", label: "⚙️ Pressão", unidade: "bar" },
  { key: "SETPOINT", label: "🎯 Setpoint", unidade: "" },
  { key: "CONTADOR", label: "🔢 Contador", unidade: "" },
  { key: "VAZAO", label: "📊 Vazão", unidade: "" },
  { key: "CMD_LIGA", label: "🟢 Status", unidade: "" },
  { key: "EMERGENCIA", label: "🚨 EMERGÊNCIA", unidade: "" },
  { key: "RESET_CONTADOR", label: "🟢 Reset Contador", unidade: "" },
  { key: "MODO_AUTOMATICO", label: "🟢 Automático", unidade: "" }
];

class App extends Component {
  state = {
    valores: {},
  };

  componentDidMount() {
    this.carregar();
    this.timer = setInterval(this.carregar, 2000);
  }

  componentWillUnmount() {
    clearInterval(this.timer);
  }
  carregar = async () => {
    const resultado = {};

    for (const tag of TAGS) {
      try {
        const data = await buscarTag(tag.key);

        // 🔹 Prioridade: boolean > int
        if (typeof data?.valorBool === "boolean") {
          resultado[tag.key] = data.valorBool;
        } else if (typeof data?.valorInt === "number") {
          resultado[tag.key] = data.valorInt;
        } else {
          resultado[tag.key] = "--";
        }

      } catch (error) {
        console.error(`Erro ao buscar ${tag.key}`, error);
        resultado[tag.key] = "--";
      }
    }

    this.setState({ valores: resultado });
  };


  render() {
    const { valores } = this.state;

    return (
      <>
        <header className="topbar">
          <h2>SUPERVISÓRIO WEB – IHM WEINTEK</h2>
        </header>

        <div className="container">
          <h1>Dashboard Industrial</h1>

          <div className="grid">
            {TAGS.map(tag => {
              const valor = valores[tag.key];

              if (typeof valor === "boolean") {
                return (
                  <Lampada
                    key={tag.key}
                    label={tag.label}
                    ligada={valor}
                    piscar={tag.key === "EMERGENCIA" && valor === true}
                  />
                );
              }

              return (
                <TagCard
                  key={tag.key}
                  label={tag.label}
                  valor={valor ?? "--"}
                  unidade={tag.unidade}
                />
              );
            })}
          </div>
        </div>
      </>
    );
  }
}

export default App;