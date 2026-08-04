import { useState } from 'react'

function App() {

  return (
    <div className="glass min-h-screen flex justify-center items-center flex-col">
      <div className='bg-primary/20 card-dash rounded-md border p-4 h-125'>
        <div className='card-title text-primary'>
         É Golpe?? Descubra!!
        </div>
        <div className='card-body'>
          <label htmlFor="mensagem" className="label text-base-content">
            Insira a mensagem que deseja analisar:
          </label>
          <input type="text" className="input" id="mensagem" />
          <label htmlFor="contexto" className="label text-base-content">
            Descreva em que contexto a mesagem foi recebida:
          </label>
          <input type="text" className="input" id="contexto" />
          <label htmlFor="empresa" className="label text-base-content">
           Qual a empresa/remetente responsável pela mensagem:
          </label>
          <input type="text" className="input" id="empresa" />
          <label htmlFor="meioComunicacao" className="label text-base-content">
           Qual o meio de comunicação utilizado:
          </label>
         <input type="text" placeholder="Ex: empresa@dominio.com" className="input" id="meioComunicacao" />
         <button className="btn btn-primary mt-4">Analisar</button>
        </div>
      </div>
    </div>
  )
}

export default App
