import React from "react";

const Message = (props) => {
    const message = props.message;

    if (!message) {
        return null;
    }

    return (
        <div className="ui negative message">
            {props.message}
        </div>
    );
}

export default Message;