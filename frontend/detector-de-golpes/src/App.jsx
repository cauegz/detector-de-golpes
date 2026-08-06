import { useState } from 'react'
import { analisarMensagem } from './services/golpeService';

function App() {
  const [loading, setLoading] = useState(false);
  const [mensagem, setMensagem] = useState("");
  const [meioComunicacao, setMeioComunicacao] = useState("");
  const [empresa, setEmpresa] = useState("");
  const [contexto, setContexto] = useState("");
  const [resposta, setResposta] = useState("");

  async function enviar(){
    setLoading(true);
    try {
      const resultado = await analisarMensagem({
        mensagem,
        meioComunicacao,
        empresa,
        contexto
      });
      setResposta(resultado)
    } catch (erro) {
      console.error("Erro:", erro);
      console.error("Resposta:", erro.response?.data);
      console.error("Status:", erro.response?.status);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="bg-gradient-to-r from-base-200 to-error-content text-primary-content min-h-screen flex justify-center items-center flex-col">
      <h1 className="text-primary text-4xl md:text-5xl lg:text-7xl font-bold mb-[4%] font-bebasneue tracking-widest">É Golpe?? Descubra!!</h1>
      <div className='bg-primary-content/20 card-dash rounded-md border p-4 h-125 font-mono'>
        <div className='card-body text-primary text-xs w-95 md:w-180 md:text-base lg:w-240 lg:text-lg'>
          <label htmlFor="mensagem"  className="label text-base-content">
            Insira a mensagem que deseja analisar:
          </label>
          <input type="text" className="input w-full" id="mensagem" onChange={(e) => setMensagem(e.target.value)}/>

          <label htmlFor="contexto" className="label text-base-content whitespace-normal">
            Descreva em que contexto a mesagem foi recebida:
          </label>
          <input type="text" className="input w-full" id="contexto" onChange={(e) => setContexto(e.target.value)}/>

          <label htmlFor="empresa" className="label text-base-content whitespace-normal">
            Qual a empresa/remetente responsável pela mensagem:
          </label>
          <input type="text" className="input w-full" id="empresa" onChange={(e) => setEmpresa(e.target.value)}/>

          <label htmlFor="meioComunicacao" className="label text-base-content whitespace-normal">
            Qual o meio de comunicação utilizado:
          </label>
          <input type="text" placeholder="Ex: empresa@dominio.com" className="input w-full placeholder:text-white/50" id="meioComunicacao" onChange={(e) => setMeioComunicacao(e.target.value)}/>

          <button className="btn btn-primary mt-4" disabled={loading} onClick={enviar}>
            {loading && <span className="loading loading-spinner loading-sm"></span>}
            {loading ? "Analisando..." : "Analisar"}
          </button>
        </div>
      </div>
      <div>
        Resultado: {JSON.stringify(resposta)}
      </div>
    </div>
  )
}

export default App
