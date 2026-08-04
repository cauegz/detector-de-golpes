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
    <div className="glass min-h-screen flex justify-center items-center flex-col">
      <div className='bg-primary/20 card-dash rounded-md border p-4 h-125'>
        <div className='card-title text-primary'>
          É Golpe?? Descubra!!
        </div>
        <div className='card-body'>
          <label htmlFor="mensagem"  className="label text-base-content">
            Insira a mensagem que deseja analisar:
          </label>
          <input type="text" className="input" id="mensagem" onChange={(e) => setMensagem(e.target.value)}/>

          <label htmlFor="contexto" className="label text-base-content">
            Descreva em que contexto a mesagem foi recebida:
          </label>
          <input type="text" className="input" id="contexto" onChange={(e) => setContexto(e.target.value)}/>

          <label htmlFor="empresa" className="label text-base-content">
            Qual a empresa/remetente responsável pela mensagem:
          </label>
          <input type="text" className="input" id="empresa" onChange={(e) => setEmpresa(e.target.value)}/>

          <label htmlFor="meioComunicacao" className="label text-base-content">
            Qual o meio de comunicação utilizado:
          </label>
          <input type="text" placeholder="Ex: empresa@dominio.com" className="input" id="meioComunicacao" onChange={(e) => setMeioComunicacao(e.target.value)}/>

          <button className="btn btn-primary mt-4" onClick={enviar}>Analisar</button>
        </div>
      </div>
      <div>
        Resultado: {JSON.stringify(resposta)}
      </div>
    </div>
  )
}

export default App
