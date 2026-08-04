import { useState } from 'react'

function App() {

  return (
    <div className="bg-gradient-to-r from-base-200 to-error-content text-primary-content min-h-screen flex justify-center items-center flex-col ">
      <h1 className="text-primary text-2xl md:text-3xl lg:text-4xl font-bold mb-[4%] font-bebasneue tracking-widest">É Golpe?? Descubra!!</h1>
      <div className='bg-primary-content/20 card-dash rounded-md border p-4 h-125 font-mono'>
        <div className='card-body'>
          <label htmlFor="mensagem" className="label text-base-content">
            Insira a mensagem que deseja analisar:
          </label>
          <input type="text" className="input w-full" id="mensagem" />
          <label htmlFor="contexto" className="label text-base-content">
            Descreva em que contexto a mesagem foi recebida:
          </label>
          <input type="text" className="input w-full" id="contexto" />
          <label htmlFor="empresa" className="label text-base-content">
           Qual a empresa/remetente responsável pela mensagem:
          </label>
          <input type="text" className="input w-full" id="empresa" />
          <label htmlFor="meioComunicacao" className="label text-base-content">
           Qual o meio de comunicação utilizado:
          </label>
         <input type="text" placeholder="Ex: empresa@dominio.com" className="input w-full placeholder:text-white/50" id="meioComunicacao" />
         <button className="btn btn-primary mt-4">Analisar</button>
        </div>
      </div>
    </div>
  )
}

export default App
