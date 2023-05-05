const Libro = ({ info }) => {
    // const [title, authors, infoLink, imageLinks] = {...info}
    const infoDeseada = (({ title, authors, infoLink, imageLinks }) => ({ title, authors, infoLink, imageLinks }))(info);

    return (
        <div className="libro">
            <a href={infoDeseada.infoLink}>
                {infoDeseada.imageLinks
                    ? <img src={infoDeseada.imageLinks.thumbnail} alt="Portada del libro" /> :
                    <img src="./generic-book.jpg" alt="Portada del libro"></img>
                }
            </a>
            <div className="info">
                {infoDeseada.title ? <h3>{infoDeseada.title}</h3>: <h3>No hay título</h3>}

                {infoDeseada.authors ? <p>{infoDeseada.authors.map(author => author + " ")}</p>: <h3>No hay autores</h3>}
            </div>


        </div>
    )

}
export default Libro;