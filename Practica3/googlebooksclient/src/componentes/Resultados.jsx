import Libro from "./Libro";

const Resultados = ({ libros }) => (

    <div className="resultados">
        {libros.map((libro, index) => {
            return <Libro key={index} info={libro.volumeInfo} />
        })}

    </div>
)


export default Resultados;