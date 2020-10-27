//
//  Webservice.swift
//  BringGlobal
//
//  Created by Angel Gabriel Ascanio Duran on 22/10/20.
//

import Foundation

class Webservice {
    
    let urlBase = "https://api.openweathermap.org/data/2.5/"
    
    func getCities(completion: @escaping ([City]?) -> ()) {
        
        guard let url = URL(string: urlBase + "/cities") else {
            fatalError("Invalid URL")
        }
        
        URLSession.shared.dataTask(with: url) { data, response, error in
            
            guard let data = data, error == nil else {
                DispatchQueue.main.async {
                    completion(nil)
                }
                return
            }
            
            let cities = try? JSONDecoder().decode([City].self, from: data)
            
            DispatchQueue.main.async {
                completion(cities)
            }
            
        }.resume();
    }
}
