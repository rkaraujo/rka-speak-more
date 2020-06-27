import React from 'react';
import './Header.css';

const Header = () => {
    return (
        <div className="fm-header">
            <h1 className="ui huge purple header">
                <i className="horizontally flipped phone icon"></i>
                <div className="content fm-header-title">Fale Mais</div>
            </h1>
        </div>
    );
}

export default Header;