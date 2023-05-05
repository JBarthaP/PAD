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
  const [mensajesStore, setMensajeStore] = useState("")

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
        setMensajeStore("")
      });


  }

  const formarString = () => {
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
    return queryString
  }


  const onBusquedaLibros = () => {
    const queryString = formarString();
    buscarLibros(queryString)
  }

  const guardarBusqueda = () => {
    const queryString = formarString()
    if (queryString !== "") {
      localStorage.setItem('busqueda', queryString)
      setMensajeStore(`Guardado la búsqueda actual`);
    }
    else {
      setMensajeStore("No puede guardar una búsqueda vacía")
    }
  }

  const recuperarBusqueda = () => {
    const queryString = localStorage.getItem('busqueda')
    if (queryString) {
      buscarLibros(queryString)
      setMensajeStore('Recuperando la búsqueda anterior...')
    }
    else {
      setMensajeStore('No has guardado ninguna búsqueda')
    }
  }


  const borrarBusqueda = () => {
    if (localStorage.getItem('busqueda')) {
      localStorage.removeItem('busqueda')
      setMensajeStore('Eliminado su historial de búsqueda')
    }
    else {
      setMensajeStore('No has guardado ninguna búsqueda')
    }
  }

  return (
    <div>
      <header>
        <h1 className='title'>Busque sus revistas o libros</h1>


        <div className='busqueda'>
          <input type="text" placeholder="Titulo" onChange={(e) => setTitulo(e.target.value)}></input>

          <input type="text" placeholder="Autores" onChange={(e) => setAutores(e.target.value)}></input>

          <button className='fas fa-search' onClick={onBusquedaLibros}>Buscar</button>

          {mensajesStore !== "" ? <p className='mensaje-warning'>{mensajesStore}</p>: null}

          <div className='botones'>
            <button className='fas fa-history' onClick={recuperarBusqueda}> Recuperar búsqueda</button>
            <button className='fas fa-archive' onClick={guardarBusqueda}> Guardar búsqueda</button>
            <button className='fas fa-trash' onClick={borrarBusqueda}> Eliminar historial</button>

          </div>
        </div>



        <h2 className='message'>{mensaje}</h2>
      </header>

      {libros !== null && <Resultados libros={libros} />}

    </div>
  );

}

export default App;
