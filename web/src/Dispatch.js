import React, { useEffect } from 'react';
import './loading.css';
import { useHistory } from 'react-router-dom';
import { isToken } from './Utilities';

const Dispatch = () => {
  const history = useHistory();
  useEffect(() => {
    if (isToken()) {
      history.push('/home')
    } else {
      history.push('/login')
    }
  }, [history]);

  return <div className="lds-dual-ring" />
}

export default Dispatch;
