//
//  ListCitiesView.swift
//  BringGlobal
//
//  Created by Angel Gabriel Ascanio Duran on 22/10/20.
//

import SwiftUI

struct ListCitiesView: View {
    
    @ObservedObject private var cityListVM = CityListViewModel()
    
    var body: some View {
        NavigationView() {
            List(self.cityListVM.cities, id: \.name) { city in
                VStack {
                    RemoteImage(url: URL(string: (city.imageUrl ?? ""))!)
                        .padding()
                    
                    HStack {
                        VStack(alignment: .leading) {
                            Text(city.name)
                                .font(.title2)
                                .foregroundColor(.primary)
                                .padding(.bottom, 2)
                            Text(city.description ?? "")
                                .font(.headline)
                                .foregroundColor(.secondary)
                                .padding(.bottom, 15)
                        }
                        .layoutPriority(100)
                        
                        Spacer()
                    }
                }
            }
            .listRowInsets(EdgeInsets())
            .background(Color("BackColor"))
            .navigationBarTitle("List of cities")
        }
    }
}

struct ListCitiesView_Previews: PreviewProvider {
    static var previews: some View {
        ListCitiesView()
    }
}
