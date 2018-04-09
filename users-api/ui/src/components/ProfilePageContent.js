import React from 'react'

const ProfilePageContent = props => {
  return(
    <div id="profile-content">
      <h1>{props.userName}</h1>
      <p>{props.firstName}</p>
      <p>{props.lastName}</p>
      <p>{props.neighborhood}</p>
      <p>{props.bio}</p>
    </div>
  )
}

export default ProfilePageContent
