//
//  City.swift
//  BringGlobal
//
//  Created by Angel Gabriel Ascanio Duran on 22/10/20.
//

import Foundation

struct City: Codable {
    let coord: Coord?
    let weather: [Weather]?
    let base: String?
    let main: Main?
    let visibility: Int?
    let wind: Wind?
    let clouds: Clouds?
    let dt: Int64?
    let sys: Sys?
    let timezone: Int?
    let id: Int?
    let name: String
    let cod: Int?
}
