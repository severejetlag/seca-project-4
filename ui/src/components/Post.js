import React from 'react'

const Post = props => {
  return(
    <li>
      <hgroup>
        <h2>{props.post.title}</h2>
        <h3>{props.post.firstName} {props.post.lastName}</h3>
        <h3>{props.post.contactDetails}</h3>
      </hgroup>
      <p>{props.post.postBody}</p>
      {
        props.adminUser ?
        <button>
          Delete Post
        </button>
        : ""
      }
    </li>
  )
}

export default Post
