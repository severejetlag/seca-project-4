import React from 'react'
import {Link} from 'react-router-dom';

const Nav = props => {
  return(
    <nav id='main-nav'>
      <ul className="nav justify-content-end">
        <li className="nav-item">
          <Link className='nav-link' to='/'>Posts</Link>
        </li>
        <li className="nav-item">
          <Link className='nav-link' to='/login'>Login</Link>
        </li>
        <li className="nav-item">
          <Link className='nav-link' to='/signup'>Signup</Link>
        </li>
        <li className="nav-item">
          <Link className='nav-link' to='/userslist'>Users</Link>
        </li>
        <li className="nav-item">
          <Link className='nav-link' to='/profile'>Profile</Link>
        </li>
      </ul>
    </nav>
  )
}

export default Nav
