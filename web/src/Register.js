import React from 'react';
import { useHistory } from 'react-router-dom';
import Api from './api';

const Register = () => {
  const history = useHistory();
   
  const [data, setData] = useState({
    email: "",
    image: "",
    password: "",
    displayName: ""
  });
  const [error, setError] = useState(false);

  const register = (event) => {
    event.preventDefault();
    Api.register(data)
    .then(
      history.push("/login")
    )
    .catch(_ => setError(true));
  }

  const handleChange = (name) => event => {
    setData(prevState => ({ ...prevState, [name]: event.taget.value}));

  }

  const goToLogin = () => history.push('/login');

  return (
    <>
      <div className="container d-flex align-items-center justify-content-center vh-100">
        <div className="card col-8 col-md-5">
          <div className="card-body">
          <form onSubmit={register}>
              <div className="mb-3">
                <label htmlFor="email" className="form-label">Email address</label>
                <input type="email" name="email" className="form-control" id="email" placeholder="name@example.com" value={data.email} onChange={handleChange("name")} />
              </div>
              <div className="mb-3">
                <label htmlFor="image" className="form-label">Image</label>
                <input type="text" name="image" className="form-control" id="image" placeholder="Image" value={data.image} onChange={handleChange("name")} />
              </div>
              <div className="mb-3">
                <label htmlFor="password" className="form-label">Password</label>
                <input type="password" className="form-control" id="password" placeholder="Password" value={data.password} onChange={handleChange("name")} />
              </div>
              <div className="mb-3">
                <label htmlFor="displayName" className="form-label">DisplayName</label>
                <input type="text" className="form-control" id="displayName" placeholder="DisplayName" value={data.displayName} onChange={handleChange("name")} />
              </div>
              <div className="d-grid gap-2">
                <p className="text-danger text-center">{error ? 'Error al loguearse' : ''}</p>
                <button type="button" className="btn btn-link" onClick={goToLogin}>Register</button>
              </div>
            </form>
          </div>
        </div>    
      </div>
    </>
  )
}

export default Register;
