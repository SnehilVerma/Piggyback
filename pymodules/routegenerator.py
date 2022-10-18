

import json
import os
from pprint import pprint



def valid_string(str):
    if str == ' ' or str == 'END' or str == '' or str == 'None':
        return False
    return True

def parse_neighbors(street):
    sname = street['FULL_NAME']
    if not valid_string(sname):
        return None,None
    
    neighbors = []
    ca = street['CrossStreetA']
    cb = street['CrossStreetB']
    if ca:
        cas = ca.split('&')
        for n in cas:
            if not valid_string(n):
                continue
            neighbors.append(n.strip())
    if cb:
        cbs = cb.split('&')
        for n in cbs:
            if not valid_string(n):
                continue
            neighbors.append(n.strip())
    return sname,neighbors


if __name__ == "__main__":
    absolute_path = os.path.dirname(__file__)
    data_path = os.path.join(absolute_path, '../data/Street_Centerlines.json')
    f = open(data_path)
    streetjson = json.load(f)
    
    streets = streetjson['features']

    routes = {}
    for street in streets:
        #fields of interest = crossstreetA, crossstreetB
        
        sname,neighbors = parse_neighbors(street['properties'])
        if sname == None or neighbors == None:
            continue

        if sname not in routes:
            routes[sname] = set()
        routes[sname].update(neighbors)
    

    for key,value in routes.items():
        routes[key]=list(routes[key])
    pprint(routes)

    dump_path = os.path.join(absolute_path, '../data/amherst_routes.json')
    with open(dump_path, "w") as f:
        json.dump(routes,f)

