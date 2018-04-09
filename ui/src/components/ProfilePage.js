import React, {Component} from 'react'
import ProfilePageContent from './ProfilePageContent'
import ProfilePageEditForm from './ProfilePageEditForm'
import Nav from './Nav'
import {Redirect} from 'react-router-dom'


class ProfilePage extends Component{
  state = {
    editIsActive: false
  }

  toggleProfileEdit = (event) => {
    event.preventDefault()
    this.setState({editIsActive: !this.state.editIsActive})
  }

  handleDeleteClick = (event) => {
    event.preventDefault()
    this.props.deleteUser(this.props.currentUser.id)
  }
  render(){
    // Redirect from Stack Overflow
    //https://stackoverflow.com/questions/43230194/how-to-use-redirect-in-the-new-react-router-dom-of-reactjs
    if(!this.props.hasCurrentUser){
      return <Redirect to='/'/>
    }

    return(
      <main id="profile-container">
        <Nav/>
        <h1>Profile Page</h1>
        <button id='edit-profile-button' onClick={this.toggleProfileEdit}>Edit Profile</button>
        <button id='delete-profile-button' onClick={this.handleDeleteClick}>Delete Profile</button>
        {
          this.state.editIsActive ?
          <ProfilePageEditForm
            currentUser={this.props.currentUser}
            updateUser={this.props.updateUser}
          />
          :
          <ProfilePageContent
            userName={this.props.currentUser.userName}
            firstName={this.props.currentUser.firstName}
            lastName={this.props.currentUser.lastName}
            neighborhood={this.props.currentUser.neighborhood}
            bio={this.props.currentUser.bio}
          />
        }
      </main>
    )
  }
}

export default ProfilePage
