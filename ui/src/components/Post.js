import React from 'react'

const Post = props => {
  return(
    <li className='post list-group-item' id={`post-${props.post.id}`} >
      <hgroup>
        <h2>{props.post.title}</h2>
        <h3>{props.post.firstName} {props.post.lastName}</h3>
        <h3>{props.post.contactDetails}</h3>
      </hgroup>
      <p>{props.post.postBody}</p>
      {
        props.adminUser ?
        <button className='btn btn-danger'>
          Delete Post
        </button>
        : ""
      }
    </li>
  )
}

export default Post
