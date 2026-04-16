using { sap.capire.products as db } from '../db/src/schema';

service AdminService {
    entity Products   as projection on db.Products;
    entity Categories as projection on db.Categories;
}
