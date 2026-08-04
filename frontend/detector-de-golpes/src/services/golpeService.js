import { api } from "./api";

export async function analisarMensagem(dados){
    const response = await api.post("/api/ia");
    return response.data;
}