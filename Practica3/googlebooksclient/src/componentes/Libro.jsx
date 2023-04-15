const Libro = ({ info }) => {
    // const [title, authors, infoLink, imageLinks] = {...info}
    const infoDeseada = (({ title, authors, infoLink, imageLinks }) => ({ title, authors, infoLink, imageLinks }))(info);

    return (
        <div className="libro">
            <a href={infoDeseada.infoLink}>
                {infoDeseada.imageLinks
                    ? <img src={infoDeseada.imageLinks.thumbnail} alt="Portada del libro" /> :
                    <img src="" alt="Portada del libro"></img>
                }
            </a>
            <div className="info">
                {infoDeseada.title.length < 80 ? <h3>{infoDeseada.title}</h3> :  
                <h3>{infoDeseada.title.substring(0,80)+"..."}</h3>}
                
                <p>{infoDeseada.authors}</p>
            </div>


        </div>
    )

}
export default Libro;