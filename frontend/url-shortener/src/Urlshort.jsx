import React, { useState } from 'react';
import axios from 'axios';
import './urlshort.css';

const UrlShort = () => {
  const [longUrl, setLongUrl] = useState('');
  const [shortUrl, setShortUrl] = useState('');
  const [shortKey, setShortKey] = useState('');
  const [error, setError] = useState('');

  const handleShorten = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/shorten', longUrl, {
        headers: {
          'Content-Type': 'text/plain',
        },
      });
      setShortUrl(response.data);
      setError('');
    } catch (err) {
      console.error('Shorten error:', err);
      setError('Failed to shorten URL');
    }
    setLongUrl('');
  };



  return (
    <div className="container" >
      <h1>URL Shortener </h1>

      {/* Shorten Section */}
      <div>
        <input
          type="text"
          placeholder="Enter long URL..."
          value={longUrl}
          onChange={(e) => setLongUrl(e.target.value)}
        />
        <button onClick={handleShorten}>Shorten</button>
        {shortUrl && (
          <p>
            Short URL: <a href={shortUrl} target="_blank">{shortUrl}</a>
          </p>
        )}
      </div>

     

      
    </div>
  );
};

export default UrlShort;
