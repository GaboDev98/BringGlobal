//
//  CityListViewModel.swift
//  BringGlobal
//
//  Created by Angel Gabriel Ascanio Duran on 22/10/20.
//

import Foundation

class CityListViewModel: ObservableObject {
    
    @Published var cities = [CityViewModel]()
    
    init() {
        
        Webservice().getCities { cities in
            
            if let cities = cities {
                self.cities = cities.map(CityViewModel.init)
            }
        }
        
    }
}

struct CityViewModel {
    
    var city: City
    
    init(product: City) {
        self.city = product
    }
    
    var description: String? {
        return self.city.weather?[0].description
    }
    
    var imageUrl: String? {
        return self.city.weather?[0].icon
    }
    
    var name: String {
        return self.city.name
    }
}
