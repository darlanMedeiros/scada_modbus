import React, { Component } from "react";
import TagCard from "./components/TagCard";
import { buscarTag } from "./services/api";

const TAGS = [
  { key: "TEMPERATURA", label: "🌡 Temperatura", unidade: "°C" },
  { key: "PRESSAO", label: "⚙️ Pressão", unidade: "bar" },
  { key: "SETPOINT", label: "🎯 Setpoint", unidade: "" },
  { key: "CONTADOR", label: "🔢 Contador", unidade: "" },
  { key: "VAZAO", label: "📊 Vazão", unidade: "" },
  { key: "STATUS", label: "🟢 Status", unidade: "" },

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
        resultado[tag.key] = data?.valorInt ?? "--";
      } catch (error) {
        console.error(`Erro ao buscar ${tag.key}`, error);
        resultado[tag.key] = "--"; // fallback seguro
      }
    }

    this.setState({ valores: resultado });
  };

  render() {
    const { valores } = this.state;

    return (
      <div className="container">
        <h1>Dashboard Industrial</h1>

        <div className="grid">
          {TAGS.map(tag => (
            <TagCard
              key={tag.key}
              label={tag.label}
              valor={valores[tag.key] ?? "--"}
              unidade={tag.unidade}
            />
          ))}
        </div>
      </div>
    );
  }
}

export default App;




