import React, { Component } from 'react';
import { Carousel } from 'react-bootstrap';
class PlanteCarousel extends Component {
    render() {
        return (
            <div className='container'>
                <div className='justify-content-md-center row'>
                    <div className='col-lg-10 col'>
                        <h1>Plant Carousel</h1>
                        <Carousel className='carousel'>
                        <Carousel.Item>
                                <img className='d-block w-100' src='/images/capeSundew.png' alt="" />
                            </Carousel.Item>
                            <Carousel.Item>
                                <img className='d-block w-100' src='/images/nepenthes.png' alt="" />
                            </Carousel.Item>
                            <Carousel.Item>
                                <img className='d-block w-100' src='/images/ping.png' alt="" />
                            </Carousel.Item>
                            <Carousel.Item>
                                <img className='d-block w-100' src='/images/sarracenia.png' alt="" />
                            </Carousel.Item>
                            <Carousel.Item>
                                <img className='d-block w-100' src='/images/venusFlytrap.png' alt="" />
                            </Carousel.Item>
                        </Carousel>
                    </div>
                </div>
            </div>
        );
    }
}
export default PlanteCarousel;