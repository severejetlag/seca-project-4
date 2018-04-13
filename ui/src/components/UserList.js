import React from 'react'
import User from './User'
const UserList = props => {
  const userComponents = props.users.map((user, index) => {
    return (
      <User
        currentUserName={props.currentUserName}
        adminUser={props.adminUser}
        user={user}
        deleteUser={props.deleteUser}
        key={index}
        index={index}
      />
    )
  })
  return(
    <ul className='list-group list-group-flush' id='user-list'>
      {userComponents}
    </ul>
  )
}


export default UserList
