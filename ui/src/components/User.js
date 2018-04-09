import React from 'react'

const User = props => {
  const isDeleteVisible = props.adminUser && props.user.userName !== props.currentUserName
  return(
    <li className='user' id={`user-${props.user.id}`}>
      <hgroup>
        <h2>{props.user.userName}</h2>
        <h3>{props.user.firstName} {props.user.lastName}</h3>
      </hgroup>
      <p>{props.user.bio}</p>
      <p>{props.user.neighborhood}</p>
      {
        isDeleteVisible ?
        <button className="admin-user-delete" onClick={() => props.deleteUser(props.user.id, props.index)}>
          Delete User
        </button>
        : ""
      }
    </li>
  )
}

export default User
