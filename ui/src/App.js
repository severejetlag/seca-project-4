import React, {Component} from 'react'
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom'
import Login from './components/Login'
import axios from 'axios'
import UsersPage from './components/UsersPage'
import Signup from './components/Signup'
import ProfilePage from './components/ProfilePage'

class App extends Component {
  state = {
    currentUser: {},
    adminUser: false,
    hasCurrentUser: false
  }

  userLogin = async (userInfo, isAdmin) => {
    try {
      console.log(userInfo)
        const userLoginResponse = await axios.get(`${process.env.REACT_APP_USERS_API}/users/search?userName=${userInfo.userName}`)
        this.setState({ currentUser: userLoginResponse.data, adminUser: isAdmin, hasCurrentUser: true})
    } catch(error) {
        console.log('Error logging in!')
        console.log(error)
    }
  }

  createUser = async (newUserInfo) =>{
    try{
      const createdUserResponse = await axios.post(`${process.env.REACT_APP_USERS_API}/users`, newUserInfo)
      this.setState({currentUser: createdUserResponse.data, hasCurrentUser: true})
    }catch(error){
      console.log('Error creating new user')
      console.log(error)
    }
  }

  updateUser = async (userInfo) =>{
    try{
      const updatedUserResponse = await axios.put(`${process.env.REACT_APP_USERS_API}/users/${userInfo.id}`,userInfo)
      console.log(updatedUserResponse)
      this.setState({currentUser: {...userInfo}})
    }catch(error){
      console.log('Error updating account information')
      console.log(error)
    }
  }

  deleteUser = async (userId) => {
    try{
      const deletedUserResponse = await axios.delete(`${process.env.REACT_APP_USERS_API}/users/${userId}`)
      console.log(deletedUserResponse)
      this.setState({currentUser:{}, hasCurrentUser:false})
    }catch(error){
      console.log('Error deleting account')
      console.log(error)
    }
  }

  toggleAdminLogin = (isAdmin) => {
    this.setState({adminUser: isAdmin})
  }

  render() {
    const LoginComponent = () => (
      <Login
        hasCurrentUser={this.state.hasCurrentUser}
        currentUser={this.state.currentUser}
        userLogin={this.userLogin}
        toggleAdminLogin={this.toggleAdminLogin}
      />)

    const SignupComponent = () => (
      <Signup
        hasCurrentUser={this.state.hasCurrentUser}
        currentUser={this.state.currentUser}
        createUser={this.createUser}
      />)

    const UsersPageCompoment = () => (
      <UsersPage
        hasCurrentUser={this.state.hasCurrentUser}
        currentUser={this.state.currentUser}
        adminUser={this.state.adminUser}
      />)

    const ProfilePageComponent = () => (
      <ProfilePage
        hasCurrentUser={this.state.hasCurrentUser}
        currentUser={this.state.currentUser}
        updateUser={this.updateUser}
        deleteUser={this.deleteUser}
      />
    )

    return (
      <Router>
        <Switch>
          <Route exact path='/' render={LoginComponent}/>
          <Route exact path='/signup' render={SignupComponent}/>
          <Route exact path='/userslist' render={UsersPageCompoment}/>
          <Route exact path='/profile' render={ProfilePageComponent}/>
        </Switch>
      </Router>
    );
  }
}

export default App;
