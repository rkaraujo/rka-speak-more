import React from 'react';

class InputDDD extends React.Component {
    state = { value: '' };

    onChangeDDD = (event) => {
        const value = event.target.value;
        this.setState({ value });
        this.props.onDDDChange(value);
    };

    render() {
        return (
            <div className="ui field">
                <label htmlFor={this.props.name}>{this.props.label}:</label>
                <input type="text" id={this.props.name} name={this.props.name} value={this.state.value} onChange={this.onChangeDDD} required />
            </div>
        );
    }
}

export default InputDDD;