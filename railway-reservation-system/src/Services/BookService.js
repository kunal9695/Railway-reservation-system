import axios from'axios'
import authHeader from './auth-header';

const BOOK_TRAIN_TICKET ='http://localhost:8080/user/book';


class BookService{
    bookTrain(UserDetails){
        return axios.post(BOOK_TRAIN_TICKET ,UserDetails, { headers: authHeader() });
    }

}
export default new BookService();