import React from 'react';

class InputTime extends React.Component {
    state = { value: '' };

    onChangeTime = (event) => {
        const value = event.target.value;
        this.setState({ value });
        this.props.onTimeChange(value);
    };

    render() {
        return (
            <div className="ui field">
                <label htmlFor="time">Tempo da ligação (minutos):</label>
                <input type="text" id="time" name="time" value={this.state.value} onChange={this.onChangeTime} required />
            </div>
        );
    }
}

export default InputTime;
