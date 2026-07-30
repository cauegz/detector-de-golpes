import { useState } from 'react'

function App() {

  return (
    <div className="min-h-screen flex justify-center items-center flex-col">
      <div className='card-xl rounded-md border p-4 h-100'>
        <div className='card-title text-primary'>
          Preencha os campos para verificar a possibilidade da mensagem ser um golpe
        </div>
        <div className='card-body'>
          <label htmlFor="mensagem_golpe" className="label text-base-content">
            Insira a mensagem que deseja analisar
          </label>
          <input type="text" className="input" id="mensagem_golpe" />
        </div>
      </div>
    </div>
  )
}

export default App
