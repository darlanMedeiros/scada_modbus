const API_URL = "http://localhost:8080/api/tags";

export async function buscarTag(nome) {
  const res = await fetch(`${API_URL}/${nome.toLowerCase()}`);
  if (!res.ok) throw new Error("Erro API");
  return res.json();
}
