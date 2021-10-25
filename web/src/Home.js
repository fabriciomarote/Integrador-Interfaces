import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import Api from './api';

const Home = () => {
  const [user, setUser] = useState({
    id: "",
    email: "",
    image: "",
    password: "",
    displayName: "",
    notes: notes
  })

  const [notes, setNotes] = useState([]);

  useEffect(() => {
    Api.getUser()
    .then( success => {
      setUser({
        id: success.data.id,
        email: success.data.email,
        image: success.data.image,
        password: success.data.password,
        displayName: success.data.displayName,
        notes: success.data.notes,
      })
    })
    .catch( error =>
      console.log(error)
    )
  },[]    
  );

  const addNote = () => {
    Api.addNote()
    .then( 
      setNotes(prevState => ({ ...prevState}))
      .catch (error => {
        console.log(error)
      })
  }

  return (
    <>
      <div className="container d-flex align-items-center justify-content-center vh-100">
        <div className="card col-8 col-md-5">
          <div className="card-body">
            <div>
              {user.notes.map( note => {
                <div>
                  <h3>{note.title}</h3>
                  <p>{note.description}</p>
                  <p>{note.lastModifiedDate}</p>
                </div>
              })
              } 
            </div>
            <div className ="btn-group">
              <button type="button" className="btn btn-primary" onClick={goToAddNote}>Add Note</button>
              <button type="button" className="btn btn-primary" onClick={goToAddNote}>Edit Note</button>
              <button type="button" className="btn btn-primary" onClick={goToAddNote}>Delete Note</button> 
            </div>
          </div>    
        </div>
      </div>
    </>
  )

}

export default Home;
