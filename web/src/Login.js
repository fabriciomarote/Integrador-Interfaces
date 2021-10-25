import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import Api from './api';

const Login = () => {
  const history = useHistory();

  const [data, setData] = useState({
    email: email,
    password: password
  });

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(false);

  const login = (event) => {
    event.preventDefault();
    Api.login(data)
      .then( 
        history.push("/home")
      )
      . catch(_ => setError(true));
  }

  const handleChange = (updateFunction) => (event) => updateFunction(event.target.value)

  const goToRegister = () => history.push('/register')

  return (
    <>
      <div className="container d-flex align-items-center justify-content-center vh-100">
        <div className="card col-8 col-md-5">
          <div className="card-body">
            <form onSubmit={login}>
              <div className="mb-3">
                <label htmlFor="email" className="form-label">Email address</label>
                <input type="email" className="form-control" id="email" placeholder="name@example.com" value={email} onChange={handleChange(setEmail)} />
              </div>
              <div className="mb-3">
                <label htmlFor="password" className="form-label">Password</label>
                <input type="password" className="form-control" id="password" placeholder="Password" value={password} onChange={handleChange(setPassword)} />
              </div>
              <div className="d-grid gap-2">
                <p className="text-danger text-center">{error ? 'Error al loguearse' : ''}</p>
                <button type="submit" className="btn btn-primary">Login</button>
                <button type="button" className="btn btn-link" onClick={goToRegister}>Register</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
}

export default Login;
