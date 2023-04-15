import { useState } from 'react';
import Resultados from './componentes/Resultados';
import { buildURI } from './utils/AsyncUtils';
import axios from "axios"

import './App.css';

function App() {

  const [titulo, setTitulo] = useState("")
  const [autores, setAutores] = useState("")
  const [libros, setLibros] = useState(null)
  const [mensaje, setMensaje] = useState("Resultados")

  const buscarLibros = (queryString) => {
    const builtURI = buildURI(queryString);
    setMensaje("Cargando...")
    axios.get(builtURI)
      .then(function (response) {
        // handle success
        if (response.data.items) {
          setLibros(response.data.items)
          setMensaje("Resultados")
        }
        else {
          setMensaje("No hay resultados")
        }
      })
      .catch(function (error) {
        // handle error
        setMensaje("Hubo un error en la búsqueda, pruebe más adelante")
      })
      .finally(function () {
        // always executed
      });


  }


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
    buscarLibros(queryString)


  }

  return (
    <div>
      <header>
        <h1 className='title'>Busque sus revistas o libros</h1>


        <div className='busqueda'>
          <input type="text" placeholder="Titulo" onChange={(e) => setTitulo(e.target.value)}></input>

          <input type="text" placeholder="Autores" onChange={(e) => setAutores(e.target.value)}></input>

          <button type="button" className='fas fa-search' onClick={onBusquedaLibros}>Buscar</button>
        </div>

        <h2 className='message'>{mensaje}</h2>
      </header>

        {libros !== null && <Resultados libros={libros} />}
        
    </div>
  );

}

export default App;
