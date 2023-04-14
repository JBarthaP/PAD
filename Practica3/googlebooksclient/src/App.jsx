import { useState } from 'react';
import Resultados from './componentes/Resultados';
import { axios_call } from './utils/AsyncUtils';
import './App.css';

function App() {

  const [titulo, setTitulo] = useState("")
  const [autores, setAutores] = useState("")
  const [libros, setLibros] = useState(null)
  const [mensaje, setMensaje] = useState("Resultados")

  const onBusquedaLibros = () => {
    let queryString = ""
    if (titulo.length !== 0) {
      queryString += `intitle:${titulo}`;
    }

    if (autores.length !== 0) {
      if (titulo.length !== 0) {
        queryString += "+";
      }
      queryString += `inauthor:${autores}`;
    }
    console.log(queryString);
    const libros_nuevos = axios_call(queryString)
    setLibros(libros_nuevos)
  }

  return (
    <div className="App">
      <h1>Busque sus revistas o libros</h1>

      <input type="text" placeholder="Titulo" onChange={(e) => setTitulo(e.target.value)}></input>

      <input type="text" placeholder="Autores" onChange={(e) => setAutores(e.target.value)}></input>

      <button type="button" onClick={onBusquedaLibros}>Buscar</button>

      <h2>{mensaje}</h2>

      {libros !== null && <Resultados libros={libros} />}

    </div>
  );
  
}

export default App;
