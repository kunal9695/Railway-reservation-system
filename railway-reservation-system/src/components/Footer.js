import "./Footer.css"


const Footer = () => {
  const year = new Date().getFullYear();

  return <footer className="footer">{`Copyright © Team 5 B02 ${year}`}</footer>;
};

export default Footer;