import React from 'react'
import {Link} from 'react-router-dom';

const Nav = props => {
  return(
    <nav id='main-nav'>
      <Link to='/'>Login</Link> |
      <Link to='/signup'>Signup</Link> |
      <Link to='/userslist'>Users</Link> |
      <Link to='/profile'>Profile</Link>
    </nav>
  )
}

export default Nav
