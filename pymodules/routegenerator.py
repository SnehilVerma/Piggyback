

import json
import os
from pprint import pprint



# seed bidirectional relations which are missing from the actual dataset.
def validate_adj_list(routes):

    missing_keys = []
    for street,nei in routes.items():
        for n in nei:
            if n not in routes:
                missing_keys.append(n)    #add missing road header.
    
    # print('bidirectional relation not found for' + 'source ' + street + ' and neighbour ' + routes[nei])
    for key in missing_keys:
        routes[key]=[]
        
    for street,nei in routes.items():
        for n in nei:
            if n in routes and street not in routes[n]:
                routes[n].append(street)
        
def valid_string(str):
    if str == ' ' or str == 'END' or str == '' or str == 'None' or str=="null":
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
            if ',' in n:
                continue
            if n.strip() == 'SOUTH EAST ST':
                neighbors.append('S EAST ST')   # STUPID CASE CHECK
            else:
                neighbors.append(n.strip())
    if cb:
        cbs = cb.split('&')
        for n in cbs:
            if not valid_string(n):
                continue
            if ',' in n:
                continue
            if n.strip() == 'SOUTH EAST ST':
                neighbors.append('S EAST ST')   # STUPID CASE CHECK
            else:
                neighbors.append(n.strip())
    return sname,neighbors


if __name__ == "__main__":
    absolute_path = os.path.dirname(__file__)
    data_path = os.path.join(absolute_path, '../data/Street_Centerlines.json')
    f = open(data_path)
    streetjson = json.load(f)
    
    streets = streetjson['features']

    routes = {}

    short_graph=False
    whitelist = ['E HADLEY RD','BRITTANY MANOR DR','TRIMWOOD LN','WHIPPLETREE LN','WINTERGREEN CIR','SOUTHPOINT DR',
    'RIVERGLADE RD','THE BROOK','JUSTICE DR','CHESTERFIELD DR','HUNTER HILL CIR','COLUMBIA DR','COLUMBIA CIR']

    # TODO: Create empty vector of each street so that we know the number of nodes in the graph.
    for street in streets:
        sname = street['properties']['FULL_NAME']
        if sname and valid_string(sname):
            routes[sname]=set()

    for street in streets:
        #fields of interest = crossstreetA, crossstreetB
        
        sname,neighbors = parse_neighbors(street['properties'])
        if sname == None or neighbors == None:
            continue

        if short_graph is True:
            if sname not in whitelist:
                continue

        if sname not in routes:
            routes[sname] = set()
        routes[sname].update(neighbors)
    

    for key,value in routes.items():
        routes[key]=list(routes[key])
    pprint(routes)

    if short_graph:
        dump_path = os.path.join(absolute_path, '../data/amherst_routes_short.json')
    else:
        dump_path = os.path.join(absolute_path, '../data/amherst_routes.json')


    validate_adj_list(routes)

    with open(dump_path, "w") as f:
        json.dump(routes,f)






